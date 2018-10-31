# NettyMeesageFramework - PostgresSQL example

## Intention
A while ago, I was doing a JDBC connector project that can connect to PostgresSQL DB. This is a challenge task as there were just
no third party SDK for connecting to PostgresSQL DB server. So the implementation had to begin with raw wire protocol and
nasty buffer reading. I was very quickly running into TCP sticky packet issue. At that time, Java Nio framework was used
for this project. This repo is a simulation of PostgresSQL server/client with basic: Auth, Query and Disconnect request/response.
But at this time, I refresh the implementation with Netty.


## PostgresSQL Wire Protocol Example
[Reading](https://www.pgcon.org/2014/schedule/attachments/330_postgres-for-the-wire.pdf)

## Implementation
The core of the implementation is Netty's powerful ChannelHander. This is a registration based list that will guide your
business logic through the information streaming. The basic process is

                                          -----------------ChannelHander-----------------
      client --- request ---> server --- | decode | process1 | process2 | encode | send | ---response---> client
                                          -----------------------------------------------
Netty's EventLoop also provides strong ability for Non-Blcoking I/O. It split the request receiving/processing/sending.
This makes Reactor programming mode possible
