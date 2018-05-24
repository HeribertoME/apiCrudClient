package com.hmelizarraraz.apicrudclient.view.interfaces;

import com.hmelizarraraz.apicrudclient.adapter.UserAdapter;
import com.hmelizarraraz.apicrudclient.pojo.User;

import java.util.ArrayList;

/**
 * Interfaz de vista principal. Se definen los métodos correspondiente exclusivamente de la vista
 */
public interface IMainView {

    /**
     * Inicializa el presentador
     */
    void setUpPresenter();

    /**
     * Realiza la petición al presentador para obtener la lista de usuarios
     */
    void getUsers();

    /**
     * Genera un linear layout manager con orientación vertical y lo setea al recyclerView
     */
    void generateLinearLayoutVertical();

    /**
     * Crea un adaptador de usuarios
     * @param users ArrayList de User con la lista de usuarios
     * @return Retorna un adaptador de usuario
     */
    UserAdapter createAdapter(ArrayList<User> users);

    /**
     * Setea el adaptador en el recyclerView
     * @param adapter El adaptador de usuario
     */
    void initializeAdapter(UserAdapter adapter);

    /**
     * Muestra un toast con mensaje de error
     * @param err El mensaje de error a mostrar
     */
    void showError(String err);

    /**
     * Visualiza el indicador de carga
     * @param show True: Para mostrarlo. False: Ocultarlo
     */
    void showProgressBar(boolean show);

    /**
     * Método que inicializa la actividad para registrar un usuario
     */
    void addUser();

}