import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ApacheKafkaComponent} from "./apache-kafka/apache-kafka.component";

const routes: Routes = [
  {path: 'apacheKafkaComponent', component: ApacheKafkaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
