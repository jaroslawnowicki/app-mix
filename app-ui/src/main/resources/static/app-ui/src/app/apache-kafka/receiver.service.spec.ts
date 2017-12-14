import {inject, TestBed} from "@angular/core/testing";

import {ReceiverService} from "./receiver.service";

describe('ReceiverService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReceiverService]
    });
  });

  it('should be created', inject([ReceiverService], (service: ReceiverService) => {
    expect(service).toBeTruthy();
  }));
});
