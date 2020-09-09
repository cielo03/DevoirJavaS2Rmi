package devoirs2.utils;


import devoirs2.java.service.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fabrique {
    private static IProduit iProduit;
    private static IEntree iEntree;
    private static ISortie iSortie;
    private static IClient iClient;
    private static ITypeClient iTypeClient;
    private static ICommande iCommande;
    private static IDetailsCommande iDetailsCommande;
    private static IProfile iProfile;
    private static IUtilisateur iUtilisateur;
    private static IFacture iFacture;


    public static void init() throws Exception{
        System.setSecurityManager(new SecurityManager());
        try{
            Registry registry = LocateRegistry.getRegistry(5003);

            iProduit = (IProduit) registry.lookup("produitRemote");
            iEntree = (IEntree) registry.lookup("entreeRemote");
            iSortie = (ISortie) registry.lookup("sortieRemote");
            iClient = (IClient) registry.lookup("clientRemote");
            iTypeClient = (ITypeClient) registry.lookup("typeClientRemote");
            iDetailsCommande = (IDetailsCommande) registry.lookup("detailsRemote");
            iCommande = (ICommande) registry.lookup("commandeRemote");
            iProfile = (IProfile) registry.lookup("profileRemote");
           iUtilisateur = (IUtilisateur) registry.lookup("utilisateurRemote");
           iFacture = (IFacture) registry.lookup("factureRemote");

        }catch (Exception e){
            throw e;
        }
    }

    public static IProduit getiProduit() throws Exception {
        try {
            if (iProduit == null) {
                init();
            }
            return iProduit;
        }catch (Exception e){
            throw e;
        }
    }

    public static IEntree getiEntree() throws Exception {
        try {
            if (iEntree == null) {
                init();
            }
            return iEntree;
        }catch (Exception e){
            throw e;
        }
    }

    public static ISortie getiSortie() throws Exception {
        try {
            if (iSortie == null) {
                init();
            }
            return iSortie;
        }catch (Exception e){
            throw e;
        }
    }
    public static IClient getiClient() throws Exception {
        try {
            if (iClient == null) {
                init();
            }
            return iClient;
        }catch (Exception e){
            throw e;
        }
    }

    public static ITypeClient getiTypeClient() throws Exception {
        try {
            if (iTypeClient == null) {
                init();
            }
            return iTypeClient;
        } catch (Exception e) {
            throw e;
        }
    }

    public static ICommande getiCommande() throws Exception {
        try {
            if (iCommande == null) {
                init();
            }
            return iCommande;
        }catch (Exception e){
            throw e;
        }
    }

    public static IDetailsCommande getiDetailsCommande() throws Exception {
        try {
            if (iDetailsCommande == null) {
                init();
            }
            return iDetailsCommande;
        }catch (Exception e){
            throw e;
        }
    }

    public static IUtilisateur getiUtilisateur() throws Exception {
        try {
            if (iUtilisateur == null) {
                init();
            }
            return iUtilisateur;
        }catch (Exception e){
            throw e;
        }
    }

    public static IProfile getiProfile() throws Exception {
        try {
            if (iProfile == null) {
                init();
            }
            return iProfile;
        }catch (Exception e){
            throw e;
        }
    }

    public static IFacture getiFacture() throws Exception {
        try {
            if (iFacture == null) {
                init();
            }
            return iFacture;
        }catch (Exception e){
            throw e;
        }
    }

}

