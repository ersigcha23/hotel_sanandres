/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enlinea.h_sanandres_rf.negocio;

import com.enlinea.h_sanandres_rf.model.Impuesto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eriksigcha
 */
@Stateless
public class ImpuestoFacade extends AbstractFacade<Impuesto> {

    @PersistenceContext(unitName = "com.enlinea_h_sanandres_rf_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpuestoFacade() {
        super(Impuesto.class);
    }
    
}
