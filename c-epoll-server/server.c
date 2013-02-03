#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/epoll.h>

#define MAX_EVENTS 1
#define MAX_BUF    1024

// Simple single thread epoll server, that prints incoming requests to the stdout.
int main() {
    int s;
    // create socket
    if ((s = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("socket error");
        exit(1);
    }
    
    struct sockaddr_in serv_addr;
    int portno = 7000;
    serv_addr.sin_family = AF_INET; // don't care IPv4 or IPv6
    serv_addr.sin_addr.s_addr = INADDR_ANY; // receive packets destined to any of the interfaces
    serv_addr.sin_port = htons(portno);
 
    // by default, system doesn't close socket, but set it's status to TIME_WAIT; allow socket reuse   
    int yes = 1;
    if (setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int)) == -1) {
        perror("setsockopt");
        exit(1);
    } 
    
    // bind socket to address
    if (bind(s, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) == -1) {
        perror("bind error");
        close(s);
        exit(1);
    }
    
    // start listening on port 7000, allow only 1 connection in the incominq queue
    if (listen(s, 1) == -1) {
        perror("listen error");
        close(s);
        exit(1);
    }
    
    // create epoll instance
    int epfd;
    epfd = epoll_create(5);
    if (epfd == -1) {
        perror("epoll_create");
    }
    
    int cs; // client socket
    struct sockaddr_storage their_addr;
    socklen_t addr_size = sizeof their_addr;
    
    char request[MAX_BUF] = "";
    struct epoll_event evlist[MAX_EVENTS];
    while (strstr(request, "close") == NULL) {
        // wait for incoming connection
        if ((cs = accept(s, (struct sockaddr *) &their_addr, &addr_size)) == -1) {
            perror("accept error");
            close(s);
            exit(1);
        }
        
        struct epoll_event ev;
        ev.data.fd = cs;
        ev.events = EPOLLIN;
        if (epoll_ctl(epfd, EPOLL_CTL_ADD, cs, &ev) == -1) {
            perror("epoll_ctl");
        }
        int ready = epoll_wait(epfd, evlist, MAX_EVENTS, -1);
        if (ready == -1) {
            perror("epoll_wait");
        }
        int j;
        for (j = 0; j < ready; j++) {
            char buf[MAX_BUF];
            int byte_count = read(evlist[j].data.fd, buf, MAX_BUF);
            if (byte_count == -1) {
                perror("read");
            }
            memcpy(request, buf, byte_count);
            puts(request);
        }
        
        char response[] = "HTTP/1.1 200 OK \n Content-Type: text/xml;charset=utf-8 \n Content-Length: 0 \n";
        if (send(cs, response, sizeof response, 0) == -1) {
            perror("send error");
            close(s);
            close(cs);
            exit(1);
        }
        close(cs);
    }
    
    close(s);
    printf("Shutdown server... \n");
}
