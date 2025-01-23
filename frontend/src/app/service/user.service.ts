import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userInfo: UserInfo = {
    name: 'Иван Иванов',
    email: 'ivan@example.com',
    city: 'Москва',
    phoneNumber: '+7 (123) 456-7890'
  };

  getUserInfo(): UserInfo {
    return this.userInfo;
  }
}

export interface UserInfo {
  name: string;
  email: string;
  city: string;
  phoneNumber: string;
}
