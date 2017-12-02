package lfernando.sistemaespecialista;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    /** Variáveis **/
    boolean cnh, chave, condicao_veiculo, funcionou;
    float combustivel = 0;

    /** Dialog **/
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        builder = new AlertDialog.Builder(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1();
            }
        });
    }

    public void dialog1(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Você possui carteira de habilitação?");
        builder.setMessage("");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cnh = true;
                dialog2();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cnh = false;
                dialogFinal();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }


    public void dialog2(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Você possui a chave do veículo?");
        builder.setMessage("");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                chave = true;
                dialog3();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                chave = false;
                dialogFinal();

            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }


    public void dialog3(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("O veículo está em boas condições?");
        builder.setMessage("Pneus, freios, iluminação, etc");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                condicao_veiculo = true;
                dialog4();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                condicao_veiculo = false;
                dialogFinal();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }


    public void dialog4(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Qual a porcentagem de combustível?");
        final SeekBar seekBar = new SeekBar(this);
        seekBar.setMax(10);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                combustivel = i;
                builder.setMessage("Valor: "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setView(seekBar);

        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog5();

            }
        });
        alertDialog = builder.create();
        alertDialog.show();

    }

    public void dialog5(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("O veículo funcionou ao dar a partida?");
        builder.setMessage("");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                funcionou = true;
                dialogFinal();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                funcionou = false;
                dialogFinal();

            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dialogFinal(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado");

        if(cnh & chave & condicao_veiculo & combustivel>0 & funcionou){
            builder.setMessage("Pode utilizar o veículo.");
        }else if (chave & cnh & combustivel > 0 & condicao_veiculo & !funcionou){
            builder.setMessage("Chame um mecânico.");
        }else if (!cnh){
            builder.setMessage("Não utilize o veículo!");
        }else if (!chave){
            builder.setMessage("Não utilize o veículo!");
        }else if (cnh & chave & combustivel==0 & condicao_veiculo & !funcionou){
            builder.setMessage("Não utilize o veículo!");
        }else if (!condicao_veiculo){
            builder.setMessage("Chame um mecânico.");
        }else if(chave & cnh & condicao_veiculo & combustivel==0 & funcionou){
            builder.setMessage("Chame um mecânico.");
        }


        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

}
