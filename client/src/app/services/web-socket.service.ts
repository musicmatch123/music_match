import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ChatMessageDto } from '../models/chatMessageDto';
import { Observable, firstValueFrom, lastValueFrom } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  webSocket!: WebSocket;
  chatMessages: ChatMessageDto[] = [];
  demoChatId: any = 1;
 // private apiBaseUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  public openWebSocket() {
   // this.webSocket = new WebSocket('ws://localhost:8080/chat');
    this.webSocket = new WebSocket('wss://dainty-tray-production.up.railway.app/chat');



    this.webSocket.onopen = (event) => {
      console.log('Open: ', event);
    };

    this.webSocket.onmessage = (event) => {
      const chatMessageDto = JSON.parse(event.data);
      this.chatMessages.push(chatMessageDto);
    };

    this.webSocket.onclose = (event) => {
      this.deleteCacheRecord();
      console.log('Close: ', event);
    };
  }

  public sendMessage(chatMessageDto: ChatMessageDto) {
    this.webSocket.send(JSON.stringify(chatMessageDto));
    this.httpClient.post( environment.apiBaseUrl + "/addCache/1/" + chatMessageDto.user + "/" + chatMessageDto.message,
    chatMessageDto).subscribe(data =>{console.log(data) });
  }

  public closeWebSocket() {
    this.webSocket.close();
    this.deleteCacheRecord();
  }

  
  cacheMessage(message: string, username: string, id: number) {
    const body = {
      'username': username,
      'message': message,
      'id': id
    }
    return lastValueFrom(this.httpClient.post( environment.apiBaseUrl+ `/saveMessageHistory`, body))
  }

  public deleteCacheRecord() {
    return this.httpClient.delete( environment.apiBaseUrl + "/delete/" + this.demoChatId).subscribe(data => {});
  }

  public getCacheRecord(id: number): Observable<any> {
    const params = new HttpParams()
                  .set('id', id)
    return this.httpClient.get(environment.apiBaseUrl + "/getMessages", {params: params});
  }
}
