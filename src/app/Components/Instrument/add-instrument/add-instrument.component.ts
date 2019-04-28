import { Component, OnInit } from '@angular/core';
import { InstrumentService } from 'src/app/Services/instrument.service';

@Component({
  selector: 'app-add-instrument',
  templateUrl: './add-instrument.component.html',
  styleUrls: ['./add-instrument.component.css']
})
export class AddInstrumentComponent implements OnInit {

  constructor(private instrumentService: InstrumentService) { }

  ngOnInit() {
  }

}
