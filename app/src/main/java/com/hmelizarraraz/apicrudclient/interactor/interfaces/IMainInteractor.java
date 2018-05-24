package com.hmelizarraraz.apicrudclient.interactor.interfaces;

import android.content.Context;

import com.hmelizarraraz.apicrudclient.pojo.User;

import java.util.ArrayList;

/**
 * Interfaz de modelo interactor. Métodos que contienen la fuente de datos
 */
public interface IMainInteractor {

    /**
     * Fuente de datos: API Rest. Obtiene los usuarios desde un WS
     * @param context Contexto de la aplicación
     * @param listener Llamada Callback para realizar una acción al presentador
     */
    void getUsersFromAPI(Context context, OnUserListener listener);

    /**
     * Fuente de datos: SQLite. Obtiene los usuarios desde una base de datos SQLite
     * @param context Contexto de la aplicación
     * @param listener Llamada Callback para realizar una acción al presentador
     */
    void getUsersFromDB(Context context, OnUserListener listener);

    /**
     * Fuente de datos: ArrayList de User Dummy. Obtiene los usuarios desde una lista de usuarios hardcodeada.
     * @param context Contexto de la aplicación
     * @param listener Llamada Callback para realizar una acción al presentador
     */
    void getUsersFromDummy(Context context, OnUserListener listener);

    /**
     * Interfaz de callback, llamadas entre el interactor y el presentador
     */
    interface OnUserListener {

        /**
         * Llamada al tener éxito al obtener los usuarios
         * @param users ArrayList de User con los usuarios obtenidos de la fuente de datos
         */
        void onGetUsersSuccess(ArrayList<User> users);

        /**
         * Llamada al tener éxito al registrar un usuario
         * @param id El id del usuario que se registró
         */
        void onRegisterSuccess(int id);

        /**
         * Llamada de error cuando el campo nombre en el formulario de registro es incorrecto, vacio o nulo
         */
        void onNameError();

        /**
         * Llamada de error cuando el campo email en el formulario de registro es incorrecto, vacio o nulo
         */
        void onEmailError();

        /**
         * Llamada de error cuando el campo password en el formulario de registro es incorrecto, vacio o nulo
         */
        void onPasswordError();

        /**
         * Llamada de error cuando el campo confimación de contraseña en el formulario de registro es incorrecto, vacio o nulo
         */
        void onPasswordConfirmationError();

        /**
         * Llamada de error cuando ocurre un error
         * @param err El mensaje de error
         */
        void onError(String err);
    }
}
