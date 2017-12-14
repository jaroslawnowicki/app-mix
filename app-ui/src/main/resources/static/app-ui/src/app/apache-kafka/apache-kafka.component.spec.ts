import {async, ComponentFixture, TestBed} from "@angular/core/testing";

import {ApacheKafkaComponent} from "./apache-kafka.component";

describe('ApacheKafkaComponent', () => {
  let component: ApacheKafkaComponent;
  let fixture: ComponentFixture<ApacheKafkaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ApacheKafkaComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApacheKafkaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
