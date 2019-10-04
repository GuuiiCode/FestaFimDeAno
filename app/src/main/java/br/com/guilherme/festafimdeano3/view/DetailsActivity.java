package br.com.guilherme.festafimdeano3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import br.com.guilherme.festafimdeano3.R;
import br.com.guilherme.festafimdeano3.constants.FimDeAnoConstants;
import br.com.guilherme.festafimdeano3.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.participar = findViewById(R.id.ch_participar);

        this.mViewHolder.participar.setOnClickListener(this);

        carregaDados();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ch_participar) {
            if (this.mViewHolder.participar.isChecked()) {
                //Salvar a presênça
                this.mSecurityPreferences.storeString(
                        FimDeAnoConstants.PRESENCA_CHAVE, FimDeAnoConstants.CONFIMAR_SIM);
            } else {
                //Salvar a ausência
                this.mSecurityPreferences.storeString(
                        FimDeAnoConstants.PRESENCA_CHAVE, FimDeAnoConstants.CONFIRMA_NAO);
            }
        }
    }

    public void carregaDados() {
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String presenca = extra.getString(FimDeAnoConstants.PRESENCA_CHAVE);
            if (presenca != null && presenca.equals(FimDeAnoConstants.CONFIMAR_SIM)) {
                this.mViewHolder.participar.setChecked(true);
            } else {
                this.mViewHolder.participar.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
        CheckBox participar;
    }
}
