package org.example.Dialog;

import org.example.Empresa.Empresa;
import org.junit.jupiter.api.*;

import java.util.HashMap;

class DialoggTest {

    HashMap<String, Empresa> empresas= new HashMap<>();
    Dialogg d = new Dialogg();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Alta Empresa con datos okey")
    void altaEmpresaDatosOK() {
        d.altaEmpresa("cif","nombre empresa","telefono");
        Assertions.assertTrue( empresas.containsKey("cif"));
    }
    @Test
    @DisplayName("Test ")
    void altaEmpresaDatosOK() {
        d.altaEmpresa("cif","nombre empresa","telefono");
        Assertions.assertTrue( empresas.containsKey("cif"));
    }
    @Test
    void altaProductoVenta() {
    }

    @Test
    void altaProductoAlquiler() {
    }

    @Test
    void borrarProducto() {
    }

    @Test
    void modificarPrecioVenta() {
    }

    @Test
    void modificarPrecioAlquiler() {
    }

    @Test
    void alquilarProducto() {
    }

    @Test
    void simularPresupuesto() {
    }

    @Test
    void buscarProducto() {
    }

    @Test
    void buscarEmpresa() {
    }
}