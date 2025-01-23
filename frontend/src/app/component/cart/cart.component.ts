import { Component, OnInit } from '@angular/core';
import { Cart } from '../../data/data.module';
import { CartService } from '../../service/cart/cart.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart?: Cart;

  constructor(private cartService: CartService,private router: Router) {}
  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.cartService.getCart().subscribe(
      (response: Cart) => {
        console.log('Cart loaded', response);
        this.cart = response;
      },
      (error) => {
        console.error('Error loading cart', error);
      }
    );
  }

  increaseQuantity(productId: number): void {
    
    this.cartService.changeProductAmount(productId, true).subscribe(
      (response: Cart) => {
        const item = this.cart?.products.find(p => p.product.id === productId);
          if (item) {
        item.quantity++;
      }
      },
      (error) => {
        console.error('Error increasing quantity', error);
      }
    );
  }

  decreaseQuantity(productId: number): void {
    this.cartService.changeProductAmount(productId, false).subscribe(
      (response: Cart) => {
        const item = this.cart?.products.find(p => p.product.id === productId);
          if (item && item.quantity > 1) {
        item.quantity--;
      }
      },
      (error) => {
        console.error('Error decreasing quantity', error);
      }
    );
  }

  removeFromCart(productId: number): void {
    this.cartService.removeProduct(productId).subscribe(
      (response: Cart) => {
        console.log('Product removed', response);
        this.loadCart();
      },
      (error) => {
        console.error('Error removing product', error);
      }
    );
  }

  checkout(route: string) {
    this.router.navigateByUrl(route);
  }
}
