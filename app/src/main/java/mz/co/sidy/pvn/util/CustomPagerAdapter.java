package mz.co.sidy.pvn.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import mz.co.sidy.pvn.R;
import mz.co.sidy.pvn.model.Imagem;

/**
 * Created by Sidónio Goenha on 05/30/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<Imagem> imagens;

    public CustomPagerAdapter(Context context, List<Imagem> imagens) {
        mContext = context;
        this.imagens = imagens;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagens.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imvPhotoSlid);
        SeekBar seekBar = (SeekBar) itemView.findViewById(R.id.sbProgresso);
        TextView textView = (TextView) itemView.findViewById(R.id.tvPageNr);

        seekBar.setProgress((position + 1) * 100 / imagens.size());
        textView.setText((position + 1) + " of " + imagens.size());
        Bitmap bmp = BitmapFactory.decodeByteArray(imagens.get(position).getBs(), 0, imagens.get(position).getBs().length);
        imageView.setImageBitmap(bmp);
        //Glide.with(mContext).load(imagens.get(position).getBs()).thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
