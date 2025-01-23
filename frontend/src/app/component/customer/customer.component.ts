import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer, CustomerInfo, CustomerInfoDto, Order } from '../../data/data.module';
import { CustomerService } from '../../service/customer/customer.service';
import { AuthService } from '../../service/auth/auth.service';
import { OrderService } from '../../service/order/order.service';


@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})

export class CustomerComponent implements OnInit {
  customerInfo?: CustomerInfoDto;
  orders: Order[] = [];
  showAllOrders = false;

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private authService: AuthService,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = +params['id'];
      this.loadCustomer();
      this.loadOrders();
    });
  }

  loadCustomer(): void {
    this.customerService.getCustomer().subscribe(
      (data: CustomerInfo) => {
        this.customerInfo = data.customerInfoDto;
      },
      error => {
        console.error('Ошибка при получении данных пользователя', error);
      }
    );
  }

  loadOrders(): void {
    this.orderService.getOrders().subscribe(
      (data: Order[]) => {
        this.orders = data.reverse();
      },
      error => {
        console.error('Ошибка при получении данных заказов', error);
      }
    );
  }

  toggleShowAllOrders(): void {
    this.showAllOrders = !this.showAllOrders;
  }

  get displayedOrders(): Order[] {
    return this.showAllOrders ? this.orders : this.orders.slice(0, 3);
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

}
