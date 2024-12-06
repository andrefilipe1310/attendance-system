import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Tab1Page } from './tab1.page';
import { AuthGuard } from '../guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: Tab1Page,
  },
  {
    path: 'tabs',
    loadChildren: () => import('../tabs/tabs.module').then(m => m.TabsPageModule),
    canActivate: [AuthGuard] // Protege as abas com o Auth Guard
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Tab1PageRoutingModule {}
