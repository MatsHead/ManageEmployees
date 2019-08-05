package pl.matshead.examapp.static_values;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public enum CellFormat {
    GREEN_LEFT(Colour.GREEN, Alignment.CENTRE),
    DARK_BLUE_LEFT(Colour.DARK_BLUE, Alignment.LEFT),
    NORMAL_CENTER(Colour.BLACK, Alignment.CENTRE);



    private WritableCellFormat writableCellFormat;

    CellFormat(Colour colour, Alignment alignment) {
        WritableFont writableFont = new WritableFont(WritableFont.createFont("Arial")
                ,WritableFont.DEFAULT_POINT_SIZE
                ,WritableFont.BOLD
                ,false
                , UnderlineStyle.NO_UNDERLINE
                ,colour);
        this.writableCellFormat =  new WritableCellFormat(writableFont);
        try {
            this.writableCellFormat.setAlignment(Alignment.CENTRE);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    public WritableCellFormat getWritableCellFormat() {
        return writableCellFormat;
    }
}
