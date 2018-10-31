# NettyMeesageFramework - PostgresSQL example

##Intention
A while ago, I was doing a JDBC connector project that can connec to PostgresSQL. This is a challenge task as there are 
no third party SDK for connecting to PostgresSQL DB server. So the implementation has to begin with raw wire protocol and
nasty buffer reading. I very quickly running into TCP sticky packet issue. At that time, I was using raw Java Nio framework
for this project. This repo is a simulation of PostgresSQL server/client with basic: Auth, Query and Disconnect request/response.
But at this time, I refresh the implementation with Netty.

##PostgresSQL Wire Protocol Example
Reading

## Implementation
The core of the implementation is Netty's powerful ChannelHander. This is a registration based list that will guide your
business logic through the information streaming. The basic process is

                                          -----------------ChannelHander-----------------
      client --- request ---> server --- | decode | process1 | process2 | encode | send | ---response---> client
                                          -----------------------------------------------
Netty's EventLoop also provides strong ability for Non-Blcoking I/O. It split the request receiving/processing/sending.
This makes Reactor programming mode possible