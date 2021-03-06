import pika, threading, time, random

class ReceiveThread(threading.Thread):
	lock = threading.Lock()
	def __init__(self, queuename, host):
		threading.Thread.__init__(self)
		ReceiveThread.lock.acquire()
		self.queuename = queuename
		self.host = host
		ReceiveThread.lock.release()
	def run(self):
		connectiontemp = pika.BlockingConnection(pika.ConnectionParameters(
		        host=self.host)) #167.205.32.46
		channeltemp = connectiontemp.channel()
		channeltemp.queue_declare(queue=self.queuename, auto_delete=True)
		channel.basic_qos(prefetch_count=1)

		def callback(ch, method, properties, body):
		    print(str(body))
		    ch.basic_ack(delivery_tag = method.delivery_tag) 

		channeltemp.basic_consume(callback, queue=self.queuename)
		channeltemp.start_consuming()


def splitstring(command):
	commandlist = command.split(" ", maxsplit=1)
	return commandlist

def qdeclare_callback(method_frame):
	print("enter callback")
	global nickname
	global queuename
	print (method_frame)
	if (not method_frame): # method_frame is a result from queue_declare:
		queuename = random.choice(rand_nick)
		nickname = queuename
		channel.queue_declare(queue=queuename, auto_delete=True, passive=True)
		
def setNickName(nickName):
	global nickname
	global queuename
	if(nickname == ""):	
		queuename = nickName
		print(queuename)
		result = channel.queue_declare(qdeclare_callback, queue=queuename,passive=True, auto_delete=True)
		nickname = queuename

		receivethread = ReceiveThread(queuename, host)
		receivethread.start()
	else:
		nickname = nickName
	print("Your nickname is ", nickname)

def joinChannel(channelname):
	channel.queue_bind(exchange=exchange, queue=queuename, routing_key=channelname)
	global listchannel
	listchannel += [channelname]
	print("You join ", channelname, " channel")

def leaveChannel(channelname):
	def removechannel(channelname):
		channel.queue_unbind(queue=queuename, exchange=exchange, routing_key=channelname)
		listchannel.remove(channelname)
		print("You leave ", channelname, " channel")
	if(channelname in listchannel):
		removechannel(channelname)
	else:
		print("No ", channelname, " channel")

def sendMessage(message):
	if(len(listchannel) > 0):
		for channelname in listchannel:
			message = "[" + channelname + "] (" + nickname + ") " + message
			channel.basic_publish(exchange=exchange, routing_key=channelname, body=message)
			message = ""
	else:
		print("No channel")

def sendMessageTo(channelname, message):
	message = "[" + channelname + "] (" + nickname + ") " + message
	if(len(listchannel) > 0):
		if(channelname in listchannel):
			channel.basic_publish(exchange=exchange, routing_key=channelname, body=message) 
		else:
			print("No ", channelname, " channel")
	else:
		print("No channel")

def exit():
	if(not nickname == ""):
		channel.queue_delete(queue=queuename)
	print("Good bye")
	connection.close()
	raise SystemExit 

def callMethod(command):		
	commandlist = splitstring(command)
	if(commandlist[0] == "/NICK"):
		setNickName(commandlist[1])
	else:
		if(commandlist[0] == "/EXIT"):	
			exit()
		else:
			if(not nickname == ""):
				if(commandlist[0] == "/JOIN"):
					joinChannel(commandlist[1])
				elif(commandlist[0] == "/LEAVE"):
					leaveChannel(commandlist[1])
				else :
					if(commandlist[0].startswith('@')):
						if(len(commandlist) > 1):
							splitting = commandlist[0].split("@", maxsplit=1)
							sendMessageTo(splitting[1], commandlist[1])
					else:
						if(not command == ""):
							sendMessage(command)
			else:
				print("Set your nickname first")

def sendthread():
	commandtemp = input()
	callMethod(commandtemp)

def main():
	global connection
	connection = pika.BlockingConnection(pika.ConnectionParameters(
	        host=host))
	global channel
	channel = connection.channel()
	channel.exchange_declare(exchange=exchange, exchange_type="topic", auto_delete=True)

	while(True):
		sendthread()

nickname = ""
queuename = ""
listchannel = []
host = "localhost" # "167.205.32.46"
connection = None
channel = None
receivethread = None
exchange = "13512023__"
rand_nick = ['af', 'ik', 'jun', 'ita', 'sina', 'bela']

main()
