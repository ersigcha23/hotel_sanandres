/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enlinea.h_sanandres_rf.negocio;

import com.enlinea.h_sanandres_rf.model.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author eriksigcha
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "com.enlinea_h_sanandres_rf_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    public Cliente getClientebycedula(String identificacion) {
        List<Cliente>lista;
        Query query = this.em.createNamedQuery(Cliente.findByIdentificacion);
        query.setParameter("identificacion",identificacion);
        lista=query.getResultList();
        if (lista.isEmpty())
            return null;
        else
            return lista.get(0);
    }
}
