import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';
@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage {

  constructor(private navCtrl: NavController) {}
  logout() {
    localStorage.removeItem('isLoggedIn');
    this.navCtrl.navigateRoot('/login');
  }

}
