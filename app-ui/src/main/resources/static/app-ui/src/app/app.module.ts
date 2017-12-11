import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";


import {AppComponent} from "./app.component";
import {SocialNetworkComponent} from "./social-network/social-network.component";
import {KafkaComponent} from "./kafka/kafka.component";


@NgModule({
  declarations: [
    AppComponent,
    SocialNetworkComponent,
    KafkaComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
