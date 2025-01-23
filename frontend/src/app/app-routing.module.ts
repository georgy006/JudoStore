import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { AboutComponent } from './component/about/about.component';
import { ProductListComponent } from './component/product-list/product-list.component';
import { ContactComponent } from './component/contact/contact.component';
import { UserAccountComponent } from './component/user-account/user-account.component';
import { LoginComponent } from './component/login/login.component';
import { CartComponent } from './component/cart/cart.component';
import { AuthGuard } from './guards/auth.guard';
import { RegistrationComponent } from './component/registration/registration.component';
import { CustomerComponent } from './component/customer/customer.component';
import { OrderComponent } from './component/order/order.component';



const routes: Routes = [
  { path: 'about', component: AboutComponent },
  { path: 'products', component: ProductListComponent,canActivate: [AuthGuard]  },
  { path: 'contacts', component: ContactComponent },
  { path: 'profile', component: CustomerComponent,canActivate: [AuthGuard]  },
  { path: 'cart', component: CartComponent},
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent},
  { path: 'order', component: OrderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }