import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../service/order/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  order: any;

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.createOrder();
  }

  createOrder(): void {
    this.orderService.createOrder().subscribe(data => {
      this.order = data;
    });
  }
}
