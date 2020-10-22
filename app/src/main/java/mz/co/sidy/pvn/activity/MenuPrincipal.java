package mz.co.sidy.pvn.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import mz.co.sidy.pvn.R;
import mz.co.sidy.pvn.activity.login.ProfileScreen;
import mz.co.sidy.pvn.model.Utilizador;
import mz.co.sidy.pvn.util.AppConstants;
import mz.co.sidy.pvn.util.BaseActivity;

public class MenuPrincipal extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //public static boolean sincronizado;
    public MenuItem searchMenuItem;
    public SearchView searchView;
    public MenuItem total;
    private FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Fragment fragment;
    private Class fragmentClass;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        TextView tvUsername = header.findViewById(R.id.tvUserName);
        TextView tvUserEmail = header.findViewById(R.id.tvUserEmail);
        CircleImageView imvUserFoto = header.findViewById(R.id.imgvFotoUser);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        imvUserFoto.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ProfileScreen.class));
        });

        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

       /* try {
            if (!verificaUtilizador()) {
                navigationView.getMenu().findItem(R.id.nav_utilizadores).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_registos).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_list_cliente).setVisible(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            if (user != null) {
                tvUserEmail.setText(user.getEmail());
                tvUsername.setText(user.getDisplayName());
                imvUserFoto.setImageURI(user.getPhotoUrl());
            }

        }

       /* try {
            Utilizador u = session.getUserDetails();
            if (u != null) {
                tvUserEmail.setText(u.getCliente() != null ? u.getCliente().getEmail() : u.getUsername());
                tvUsername.setText(u.getCliente() != null ? u.getCliente().getNome() : u.getUsername());

                if (u.getCliente() != null && u.getCliente().getFoto() != null) {
                    if (u.getCliente().getFoto().getBs() != null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(u.getCliente().getFoto().getBs(), 0, u.getCliente().getFoto().getBs().length);
                        imvUserFoto.setImageBitmap(bmp);
                        //Glide.with(this).load(u.getCliente().getFoto().getBs()).thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imvUserFoto);
                    }
                }
            }
        } catch (IOException e) {
            mensagem(e.getMessage(), this);
            e.printStackTrace();
        }*/

       // backToHome();

        //sincronizado = false;
    }

    @Override
    public void onBackPressed() {
        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        total = menu.findItem(R.id.total);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        uncheckAllMenuItems(navigationView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the drawerToggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawerLayout toggls
        toggle.onConfigurationChanged(newConfig);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        uncheckAllMenuItems(navigationView);

        /*switch (item.getItemId()) {
            case R.id.nav_home:
                fragmentClass = ListaViaturasHomeFragment.class;
                setTitle(getString(R.string.title_activity_menu_principal));
                break;
            case R.id.nav_reg_cliente:
                Intent intent = new Intent(this, RegistoUtilizador.class);
                intent.putExtra(AppConstants.NOVO_UTILIZADOR, AppConstants.NOVO_UTILIZADOR);
                startActivity(intent);
                break;
            case R.id.nav_reg_viatura:
                startActivity(new Intent(this, RegistoViatura.class));
                break;
            case R.id.nav_list_cliente:
                //fragmentClass = ListaClientes.class;
                startActivity(new Intent(this, ListaClientes.class));
                //setTitle(item.getTitle());
                break;
            case R.id.nav_list_viatura:
                fragmentClass = ListaViaturasFragment.class;
                setTitle(item.getTitle());
                break;
            case R.id.nav_utilizadores:
                //fragmentClass = ListaUtilizadoresFragment.class;
                startActivity(new Intent(this, ListaUtilizadores.class));
                //setTitle(item.getTitle());
                break;
            case R.id.nav_parques:
                fragmentClass = ListaParquesFragment.class;
                setTitle(item.getTitle());
                break;
            case R.id.nav_favoritos:
                fragmentClass = FavoritosFragment.class;
                setTitle(item.getTitle());
                break;
            case R.id.nav_definicoes:
                startActivity(new Intent(this, Settings.class));
                break;

            case R.id.nav_sync:
                if (cf.getConnectivityStatus())
                    new SincronizacaoViatura(context).execute();
                break;
            case R.id.nav_logout:
                signOut();
                break;
            default:
                fragmentClass = ListaViaturasHomeFragment.class;
        }*/

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentManager.beginTransaction().replace(R.id.framLayout, fragment).commit();

        item.setChecked(true);

        drawerLayout.closeDrawers();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void backToHome() {
        //
        //fragmentClass = ListaViaturasHomeFragment.class;
        setTitle(getString(R.string.title_activity_menu_principal));
        uncheckAllMenuItems(navigationView);
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fragmentManager.beginTransaction().replace(R.id.framLayout, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    /**
     * Este metodo serve para des selecionar o item anterioremente selecionado.
     *
     * @param navigationView - O navigation view.
     */
    private void uncheckAllMenuItems(NavigationView navigationView) {
        final Menu menu = navigationView.getMenu();

        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);

            if (item.hasSubMenu()) {
                SubMenu subMenu = item.getSubMenu();

                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    subMenuItem.setChecked(false);
                }
            } else {
                item.setChecked(false);
            }
        }
    }

    public boolean verificaUtilizador() throws IOException {
        /*Utilizador u = session.getUserDetails();
        if (u != null) {
            if (u.getNivel() != null && u.getNivel().equals("ADMIN"))
                return true;
        }*/
        return false;
    }
}
