package com.rzaninelli.trainningapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.entities.TipoFisico;

import java.util.List;

public class TipoFisicoAdapter extends BaseAdapter {

    Context context;
    List<TipoFisico> tiposFisicos;

    private static class TipoFisicoHolder {
        public ImageView imageViewTipoFisicoImagem;
        public TextView textViewTipoFisicoNome;
    }

    public TipoFisicoAdapter(Context context, List<TipoFisico> tiposFisicos) {
        this.context = context;
        this.tiposFisicos = tiposFisicos;
    }

    @Override
    public int getCount() {
        return tiposFisicos.size();
    }

    @Override
    public Object getItem(int i) {
        return tiposFisicos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TipoFisicoHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_tipo_fisico, viewGroup, false);

            holder = new TipoFisicoHolder();

            holder.imageViewTipoFisicoImagem = view.findViewById(R.id.imageViewTipoFisicoImagem);
            holder.textViewTipoFisicoNome = view.findViewById(R.id.textViewTipoFisicoNome);

            view.setTag(holder);
        }
        else {
            holder = (TipoFisicoHolder) view.getTag();
        }

        holder.imageViewTipoFisicoImagem.setImageDrawable(tiposFisicos.get(i).getImagemTipoFisico());
        holder.textViewTipoFisicoNome.setText(tiposFisicos.get(i).getNome());

        return view;
    }
}
