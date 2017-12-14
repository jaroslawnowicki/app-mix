import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {SocialNetworkRoutingModule} from "./social-network-routing.module";
import {KeycloakModule} from "@ebondu/angular2-keycloak";


@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    HttpModule,
    FormsModule,
    SocialNetworkRoutingModule,
    KeycloakModule
  ],
  declarations: []
})
export class SocialNetworkModule {
}
