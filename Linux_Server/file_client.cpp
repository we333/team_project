#include "utility.h"

#define SEND_FILE ("target.png")
#define RECV_FILE ("newfile.png")

int main(int ac, char *av[])
{
	int sockfd = make_client_socket(IP, PORT);
	char buf[BUFSIZ]; bzero(buf, BUFSIZ);
	char buf2[BUFSIZ]; bzero(buf2, BUFSIZ);

	FILE *f, *f2;
	if(NULL == (f = fopen(SEND_FILE, "rb")))
		myErr;

	Try(send(sockfd, "sendfile", sizeof("sendfile"), 0))

	int len = 0;
	while(len = fread(buf, sizeof(char), BUFSIZ, f))
	{
		cout<<"send "<<len<<" bytes"<<endl;
		Try(send(sockfd, buf, len, 0))
	}

	fclose(f);
	close(sockfd);

	return 0;
}