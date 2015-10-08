import pika, threading, time

# class ReceiveThread(threading.Thread):
# 	def __init__(self, queuename):
# 		threading.Thread.__init__(self)
# 		self.queuename = queuename
# 	def run(self):
# 		connectiontemp = pika.BlockingConnection(pika.ConnectionParameters(
# 		        host='167.205.32.46'))
# 		channeltemp = connectiontemp.channel()
# 		channeltemp.queue_declare(queue=self.queuename)
# 		channel.basic_qos(prefetch_count=1)
# 		channeltemp.basic_consume(callback,
# 		                      queue=self.queuename)
# 		channeltemp.start_consuming()
# 	def callback(ch, method, properties, body):
# 	    print ("%r" % (body, )) #belum tentu benar
# 	    time.sleep( body.count('.') )
# 	    ch.basic_ack(delivery_tag = method.delivery_tag)

def main():
	def sendthread():
		def callMethod(command):
			def splitstring(command):
				nonlocal commandlist
				commandlist = command.split(" ", maxsplit=1)

			def setNickName(nickName):
				nickname = '13512023_' + nickName
				result = channel.queue_declare(exclusive=True)
				queuename = result.method.queue
				# thread = ReceiveThread(queuename)
				# thread.start()
				print("Your nickname is ", nickname)

			def joinChannel(channelname):
				channelname = '13512023_' + channelname
				channelname.exchange_declare(exchange=channelname, type='fanout')
				channel.queue_bind(exchange=channelname, queue=queuename)
				listchannel += channelname
				print("You join %s channel", channelname)

			def leaveChannel(channelname):
				def removechannel(channelname):
					listchannel.remove(channelname) #dari bindingnya belum diremove
					print("You leave %s channel", channelname)
				if(channelname in listchannel):
					removechannel(channelname)
				else:
					print("No %s channel", channelname)
			def sendMessage(message):
				message = " (" + nickname + ") " + message
				for channelname in listchannel:
					channel.basic_publish(exchange=channelname, routing_key='', body=message)

			def sendMessageTo(channelname, message):
				message = " (" + nickname + ") " + message
				channel.basic_publish(exchange=channelname, routing_key='', body=message)
				
			commandlist = []
			splitstring(command)
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
							splitting = commandlist[1].split(" ", maxsplit=1)
							sendMessageTo(splitting[0], splitting[1])
						else:
							sendMessage(commandlist[1])
		commandtemp = input()
		callMethod(commandtemp)

	nickname = ""
	listchannel = []
	host = '167.205.32.46'

	connection = pika.BlockingConnection(pika.ConnectionParameters(
	        host=host))
	channel = connection.channel()

	while(True):
		sendthread()

global nickname
global queuename
global listchannel
global listchannelthread # type = exchange
global host
global connection
global channel
main()
