import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import { AboutComponent } from './component/about/about.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from "./component/header/header.component";
import { FooterComponent } from './component/footer/footer.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ProductListComponent } from './component/product-list/product-list.component';
import { LoginComponent } from './component/login/login.component';
import { FormsModule } from '@angular/forms';
import { CartComponent } from './component/cart/cart.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { RegistrationComponent } from './component/registration/registration.component';
import { CustomerComponent } from './component/customer/customer.component';
import { CommonModule } from '@angular/common';
import { OrderComponent } from './component/order/order.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';





@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        AboutComponent,
        HeaderComponent,
        FooterComponent,
        ProductListComponent,
        LoginComponent,
        CartComponent,
        RegistrationComponent,
        CustomerComponent,
        OrderComponent
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
        provideAnimationsAsync('noop')
    ],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        CommonModule,
        MatSnackBarModule,
        MatSliderModule,
        BrowserAnimationsModule
    ]
})
export class AppModule { }