import { Component, OnInit } from '@angular/core';
import { CartService } from '../../service/cart/cart.service';
import { ProductService } from '../../service/product/product.service';
import { Cart, Category, Product } from '../../data/data.module';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  categories: Category[] = [];
  sortDirection: string = 'ASC'; // По умолчанию сортировка по возрастанию
  sortByName: boolean = true; // По умолчанию сортировка по имени
  selectedCategoryId: number | null = null;
  minPrice: number | null = null;
  maxPrice: number | null = null;
  

  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
    this.productService.getCategory().subscribe(data => {
      this.categories = data;
    });
  }
  loadProductsBySort(): void {
    this.productService.getProductsBySort(this.sortDirection, this.sortByName).subscribe(data => {
      this.products = data;
    });
  }
  loadProductsByFilter(): void {
    this.productService.filterProducts(
      this.selectedCategoryId !== null ? this.selectedCategoryId : undefined,
      this.minPrice !== null ? this.minPrice : undefined,
      this.maxPrice !== null ? this.maxPrice : undefined
    ).subscribe(data => {
      this.products = data;
    });
  }

  addToCart(product: Product, quantity: number): void {
    this.cartService.addToCart(product.id, quantity).subscribe(
      response => {
        console.log('Product added to cart:', response);
        this.snackBar.open('Добавлено в корзину', undefined, {
          duration: 2000, // Длительность отображения уведомления в миллисекундах
          horizontalPosition: 'center', // Позиция по горизонтали
          verticalPosition: 'bottom', // Позиция по вертикали
          panelClass: ['snackbar-custom'] // Класс для пользовательских стилей
        });
      },
      error => {
        console.error('Ошибка при добавлении в корзину:', error);
        this.snackBar.open('Ошибка при добавлении в корзину', undefined, {
          duration: 2000,
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
          panelClass: ['snackbar-custom']
        });
      }
    );
  }

  // Метод для изменения направления сортировки
  changeSortDirection(direction: string): void {
    this.sortDirection = direction;
    this.loadProductsBySort();
  }

  // Метод для изменения поля сортировки
  changeSortField(byName: boolean): void {
    this.sortByName = byName;
    this.loadProductsBySort();
  }

  applyFilter(): void {
    this.loadProductsByFilter();
  }

  resetFilter(): void {
    this.selectedCategoryId = null;
    this.minPrice = null;
    this.maxPrice = null;
    this.loadProductsByFilter();
  }
}
