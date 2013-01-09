#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

// Simple single thread server, that prints incoming requests to the stdout.
// Error handling disabled for code readability.
int main() {
    int s = socket(AF_INET, SOCK_STREAM, 0);
    
    struct sockaddr_in serv_addr;
    int portno = 7000;
    serv_addr.sin_family = AF_INET; // don't care IPv4 or IPv6
    serv_addr.sin_addr.s_addr = INADDR_ANY; // receive packets destined to any of the interfaces
    serv_addr.sin_port = htons(portno);
    
    // by default, system doesn't close socket, but set it's status to TIME_WAIT; allow socket reuse   
    int yes = 1;
    setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int));
    
    bind(s, (struct sockaddr *) &serv_addr, sizeof(serv_addr));
    
    // start listening on port 6666, allow only 1 connection in the incominq queue
    listen(s, 1);
    
    int cs; // client socket
    struct sockaddr_storage their_addr;
    socklen_t addr_size = sizeof their_addr;
    
    char request[1024] = ""; // 1 KB
    while (strstr(request, "close") == NULL) {
        // wait for incoming connection
        cs = accept(s, (struct sockaddr *) &their_addr, &addr_size);
        
        char buf[1024]; // 1 KB
        int byte_count = recv(cs, buf, sizeof buf, 0);
        memcpy(request, buf, byte_count);
        puts(request);
        
        char response[] = "HTTP/1.1 200 OK \n Content-Type: text/xml;charset=utf-8 \n Content-Length: 0 \n";
        send(cs, response, sizeof response, 0);
        close(cs);
    }
    
    close(s);
    printf("Shutdown server... \n");
}
