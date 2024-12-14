/*import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));*/
  import { bootstrapApplication } from '@angular/platform-browser';
  import { AppComponent } from './app/app.component';
  import { importProvidersFrom } from '@angular/core';
  import { provideRouter } from '@angular/router';
  import { routes } from './app/app.routes';
  import {provideHttpClient} from '@angular/common/http'; // A routing konfiguráció
  
  bootstrapApplication(AppComponent, {
    providers: [provideRouter(routes), provideHttpClient()]
  });
  
  