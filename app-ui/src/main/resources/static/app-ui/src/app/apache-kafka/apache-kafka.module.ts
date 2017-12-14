import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {ApacheKafkaRoutingModule} from "./apache-kafka-routing.module";
import {ReceiverComponent} from "./receiver/receiver.component";
import {ApacheKafkaComponent} from "./apache-kafka.component";

@NgModule({
  imports: [
    CommonModule,
    ApacheKafkaRoutingModule
  ],
  declarations: [ReceiverComponent, ApacheKafkaComponent]
})
export class ApacheKafkaModule {
}
