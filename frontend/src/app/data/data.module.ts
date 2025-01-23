export interface Cart {
  id: number;
  customer: Customer;
  products: ProductsCart[];
}

export interface Category {
  id: number;
  name: string;
  products: Product[];
}

export interface Customer {
  id: number;
  name: string;
  email: string;
  city: string;
  phoneNumber: string;
  orders: Order[];
  cart: Cart;
}

export interface CustomerInfo {
  customerInfoDto: CustomerInfoDto
}
export interface CustomerInfoDto {
  name: string;
  email: string;
  city: string;
  phoneNumber: string;
}

export interface Order {
  id: number;
  ordersDate: string;
  totalPrice: number;
  stockQuantity: number;
  orderStatus: OrderStatus;
  customer: Customer;
  orderItems: OrderItem[];
}

export interface OrderItem {
  id: number;
  order: Order;
  product: Product;
  quantity: number;
}

export interface OrderStatus {
  id: number;
  name: string;
  orders: Order[];
}

export interface OrderItemDto {
  productDto: {
    productName: string;
    category: { name: string };
    price: number;
  };
  quantity: number;
}

export interface Order {
  customerName: string;
  orderItemDtos: OrderItemDto[];
  dateTime: string;
  totalPrice: number;
}

export interface Product {
  id: number;
  productName: string;
  price: number;
  stockQuantity: number;
  category: Category;
  orderItems: OrderItem[];
  productsCarts: ProductsCart[];
}

export interface ProductsCart {
  id: number;
  cart: Cart;
  product: Product;
  quantity: number;
}

export interface RefreshToken {
  id: number;
  token: string;
  customer: Customer;
}

export interface BlacklistedToken {
  id: number;
  token: string;
  customer: Customer;
}
