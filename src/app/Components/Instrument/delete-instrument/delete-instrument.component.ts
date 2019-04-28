import { Component, OnInit } from '@angular/core';
import { InstrumentService } from 'src/app/Services/instrument.service';

@Component({
  selector: 'app-delete-instrument',
  templateUrl: './delete-instrument.component.html',
  styleUrls: ['./delete-instrument.component.css']
})
export class DeleteInstrumentComponent implements OnInit {

  constructor(private instrumentService: InstrumentService) { }

  ngOnInit() {
  }

}
