package br.com.guilherme.festafimdeano3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.guilherme.festafimdeano3.R;
import br.com.guilherme.festafimdeano3.constants.FimDeAnoConstants;
import br.com.guilherme.festafimdeano3.data.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    public SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.data_atual = findViewById(R.id.txt_data_atual);
        this.mViewHolder.dias_restantes_fim_ano = findViewById(R.id.txt_dias_restantes_fim_ano);
        this.mViewHolder.confirma = findViewById(R.id.btn_confirma);

        this.mViewHolder.confirma.setOnClickListener(this);

        //Data atual do sistema
        this.mViewHolder.data_atual.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        //Dias restantes para o fim do ano
        this.mViewHolder.dias_restantes_fim_ano.setText(
                String.format("%s %s", String.valueOf(contagem_de_dias()), getString(R.string.dias)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        verificaPresenca();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_confirma) {
            String presenca = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCA_CHAVE);

            Intent it = new Intent(this, DetailsActivity.class);
            it.putExtra(FimDeAnoConstants.PRESENCA_CHAVE, presenca);
            startActivity(it);
        }
    }

    public void verificaPresenca() {
        String presenca = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCA_CHAVE);
        if (presenca.equals("")) {
            this.mViewHolder.confirma.setText(getString(R.string.nao_confirmado));
        } else if (presenca.equals(FimDeAnoConstants.CONFIMAR_SIM)) {
            this.mViewHolder.confirma.setText(getString(R.string.sim));
        }else{
            this.mViewHolder.confirma.setText(getString(R.string.nao));
        }
    }

    public int contagem_de_dias() {
        //Número atual de dias no ano
        Calendar diaHoje = Calendar.getInstance();
        int hoje = diaHoje.get(Calendar.DAY_OF_YEAR);

        //Número final de dias no ano
        Calendar dataFinal = Calendar.getInstance();
        int finalAno = dataFinal.getActualMaximum(Calendar.DAY_OF_YEAR);

        return finalAno - hoje;
    }

    private static class ViewHolder {
        TextView data_atual;
        TextView dias_restantes_fim_ano;
        Button confirma;
    }
}
