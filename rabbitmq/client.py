import pika, threading, time

class ReceiveThread(threading.Thread):
	lock = threading.Lock()
	def __init__(self, queuename):
		threading.Thread.__init__(self)
		ReceiveThread.lock.acquire()
		self.queuename = queuename
		ReceiveThread.lock.release()
	def run(self):
		print("Queue : ", self.queuename)
		connectiontemp = pika.BlockingConnection(pika.ConnectionParameters(
		        host='167.205.32.46'))
		channeltemp = connectiontemp.channel()
		channeltemp.queue_declare(queue=self.queuename)
		channel.basic_qos(prefetch_count=1)

		def callback(ch, method, properties, body):
		    print ("%r" % (body, )) #belum tentu benar
		    time.sleep( body.count('.') )
		    ch.basic_ack(delivery_tag = method.delivery_tag)

		channeltemp.basic_consume(callback, queue=self.queuename)
		channeltemp.start_consuming()



def splitstring(command):
	commandlist = command.split(" ", maxsplit=1)
	return commandlist

def setNickName(nickName):
	global nickname
	nickname = '13512023_' + nickName
	result = channel.queue_declare(queue=nickname, durable=True)
	global queuename
	queuename = nickname
	channel.queue_bind(exchange=exchange, queue=queuename)
	thread = ReceiveThread(queuename)
	thread.start()
	global listchannelthread
	listchannelthread += [thread]
	print("Your nickname is ", nickname)

def joinChannel(channelname):
	channelname = '13512023_' + channelname
	channel.queue_bind(exchange=exchange, queue=queuename, routing_key=channelname)
	global listchannel
	listchannel += [channelname]
	print("You join  channel")

def leaveChannel(channelname):
	def removechannel(channelname):
		listchannel.remove(channelname) #dari bindingnya belum diremove
		print("You leave ", channelname, " channel")
	if(channelname in listchannel):
		removechannel(channelname)
	else:
		print("No ", channelname, " channel")
def sendMessage(message):
	message = " (" + nickname + ") " + message
	for channelname in listchannel:
		channel.basic_publish(exchange=exchange, routing_key=channelname, body=message)

def sendMessageTo(channelname, message):
	message = " (" + nickname + ") " + message
	channel.basic_publish(exchange=exchange, routing_key=channelname, body=message)

def callMethod(command):		
	commandlist = splitstring(command)
	if(commandlist[0] == "/NICK"):
		setNickName(commandlist[1])
	else:
		if(not nickname == ""):
			if(commandlist[0] == "/JOIN"):
				joinChannel(commandlist[1])
			elif(commandlist[0] == "/LEAVE"):
				leaveChannel(commandlist[1])
			elif(commandlist[0] == "/EXIT"):
				connection.close()
				exit()
			else:
				if(commandlist[0].startswith('@')):
					splitting = commandlist[0].split("@", maxsplit=1)
					sendMessageTo(splitting[1], commandlist[1])
				else:
					sendMessage(command)

def sendthread():
	commandtemp = input()
	callMethod(commandtemp)

def main():
	global connection
	connection = pika.BlockingConnection(pika.ConnectionParameters(
	        host=host))
	global channel
	channel = connection.channel()
	channel.exchange_declare(exchange="13512023", type="fanout")

	while(True):
		sendthread()


nickname = ""
queuename = ""
listchannel = []
listchannelthread = []
host = '167.205.32.46'
connection = None
channel = None
exchange = "13512023"

main()
