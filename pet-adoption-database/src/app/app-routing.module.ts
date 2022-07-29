import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RequestPageComponent } from './components/request-page/request-page.component';

const routes: Routes = [
  {path: 'requests', component:RequestPageComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
