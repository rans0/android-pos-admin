package suryapro.ustmobile.pos_admin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InvoiceActivity extends AppCompatActivity {
    EditText etNama, etOrderNum, etKasir;
    Button btnInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        etNama = findViewById(R.id.etNama);
        etOrderNum = findViewById(R.id.etOrderNum);
        etKasir = findViewById(R.id.etKasir);
        btnInvoice = findViewById(R.id.btnInvoice);

        btnInvoice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String orderNum = etOrderNum.getText().toString();
                String kasir = etKasir.getText().toString();

                try {
                    createPdf(nama, orderNum, kasir);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createPdf(String nama, String orderNum, String kasir) throws FileNotFoundException{
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "invoice0.pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A6);
        document.setMargins(0, 0,0, 0);

        Paragraph invoice = new Paragraph("Invoice MaKuy").setBold().setFontSize(24).setTextAlignment(TextAlignment.CENTER);
        Paragraph tenant = new Paragraph("MakanKuy\n"+
                "Jl. Scientia Boulevard, Gading, Kec. Serpong, Tangerang, Banten 15227")
                .setTextAlignment(TextAlignment.CENTER).setFontSize(12);
        Paragraph details = new Paragraph("Details").setTextAlignment(TextAlignment.CENTER).setFontSize(18);

        float[] width = {100f, 100f};
        Table table = new Table(width);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        table.addCell(new Cell().add(new Paragraph("Nama Customer")));
        table.addCell(new Cell().add(new Paragraph(nama)));

        table.addCell(new Cell().add(new Paragraph("No Order")));
        table.addCell(new Cell().add(new Paragraph(orderNum)));

        table.addCell(new Cell().add(new Paragraph("Kasir")));
        table.addCell(new Cell().add(new Paragraph(kasir)));



        BarcodeQRCode qrCode = new BarcodeQRCode(nama + '\n' + orderNum + '\n' + kasir);

        PdfFormXObject qrCodeObject = qrCode.createFormXObject(ColorConstants.BLACK, pdfDocument);
        Image qrCodeImage = new Image(qrCodeObject).setWidth(80).setHorizontalAlignment(HorizontalAlignment.CENTER);


        document.add(invoice);
        document.add(tenant);
        document.add(details);
        document.add(table);
        document.add(qrCodeImage);
        document.close();
        Toast.makeText(this,"pdf created", Toast.LENGTH_LONG).show();
    }
}