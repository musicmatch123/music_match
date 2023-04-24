import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ChatMessageDto } from '../models/chatMessageDto';
import { WebSocketService } from '../services/web-socket.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {

  //chatMessageDtoList: ChatMessageDto[] = [];
  existUser: any = '';
  currUser: string = localStorage.getItem('user')!;
  chatId: number =1;
  chatSub$!: Subscription
  msgObj: ChatMessageDto[] =[]

  constructor(public webSocketService: WebSocketService) { }

  ngOnInit(): void {
    this.msgObj = [];
    this.webSocketService.openWebSocket();
    this.chatSub$ = this.webSocketService.getCacheRecord(this.chatId).subscribe(data => {
      console.log("cache data:: ", data);
      // debugger;
      
      if(data !== null && data !== undefined && data !== '') {
        /*let msgObj = data.body.messages;
        msgObj.forEach(element => {
          const chatMessageDto = new ChatMessageDto(element.name, element.text);
          this.webSocketService.chatMessages.push(chatMessageDto);
        });*/
        this.msgObj = data.messages;
        this.msgObj.forEach((msg) => {
          this.existUser = msg.user;;
          const chatMessageDto = new ChatMessageDto(msg.user, msg.message);
          this.webSocketService.chatMessages.push(chatMessageDto);
        })
        
      }
    });
  }

  ngOnDestroy(): void {
    this.webSocketService.closeWebSocket();
    this.chatSub$.unsubscribe();
  }

  sendMessage(sendForm: NgForm) {
    const chatMessageDto = new ChatMessageDto(sendForm.value.user, sendForm.value.message);
    const user = sendForm.value.user;
    const message = sendForm.value.message;
    // const id = JSON.parse(localStorage.getItem("chatId")!);
    // this.webSocketService.sendMessage(chatMessageDto);
    this.webSocketService.cacheMessage(message, user, this.chatId).then (response => {
      console.log(response);
      this.ngOnInit();
    }).catch(err=> {
      console.error(err);
    })
    sendForm.controls['message'].reset();
  }

}
