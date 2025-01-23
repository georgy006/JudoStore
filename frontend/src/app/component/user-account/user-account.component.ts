import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth/auth.service';

interface UserInfo {
  name: string;
  email: string;
  city: string;
  phoneNumber: string;
  address?: string;
  postalCode?: string;
  birthDate?: Date;
  gender?: string;
}

interface Order {
  date: Date;
  id: string;
  total: number;
}

interface FavoriteItem {
  name: string;
}

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {
  userInfo: UserInfo = {
    name: '',
    email: '',
    city: '',
    phoneNumber: ''
  };
  orderHistory: Order[] = [];
  favoriteItems: FavoriteItem[] = [];

  constructor(private authService: AuthService) {}
  
  ngOnInit(): void {
    this.userInfo = {
      name: 'Иван Иванов',
      email: 'ivan@example.com',
      city: 'Москва',
      phoneNumber: '+7 123 456 7890'
    };

    this.orderHistory = [
      { date: new Date('2023-01-01'), id: '123456', total: 100.00 },
      { date: new Date('2023-02-15'), id: '123457', total: 200.00 }
    ];

    this.favoriteItems = [
      { name: 'Товар 1' },
      { name: 'Товар 2' }
    ];
  }

  logout(): void {
    console.log('Выход из системы');
      this.authService.logout().subscribe(
        response => {
          console.log('Logout successful', response);
        }, error => {
          console.log('Logout failed');
        }
    );
  }

  removeFromFavorites(item: FavoriteItem): void {
    this.favoriteItems = this.favoriteItems.filter(favItem => favItem !== item);
    console.log(`${item.name} удален из избранного`);
  }
}
