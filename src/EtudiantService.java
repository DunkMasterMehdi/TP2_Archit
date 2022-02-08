import java.sql.SQLException;
import java.util.ArrayList;

public class EtudiantService {
	
	private IJournal journal;
	private I_EtudiantRepository etudRep;
	private I_UniversiteRepository univRep;
	
	
	public EtudiantService(I_EtudiantRepository etudRep, I_UniversiteRepository univRep, IJournal comp) 
	{
		this.journal = comp;
		this.etudRep = etudRep;
		this.univRep = univRep;
	}
	
	
	
	boolean inscription (int matricule, String nom, String pr�nom, String email,String pwd, int id_universite) throws SQLException	
	{
	    
	    Etudiant stud = new Etudiant(matricule, nom, pr�nom, email, pwd, id_universite);
	    Universite univ = univRep.GetById(id_universite);
	    AffichageEnrichir.setSender("EtudiantService");
	    
	    journal.outPut_Msg("Log: d�but de l'op�ration d'ajout de l'�tudiant avec matricule "+matricule);
	    
	    
	    if(email == null || email.length() == 0)
	    {
	    	return false;
	    }
	    
	    if (etudRep.Exists(matricule))
	    {
	        return false;
	    }
	    
		if (etudRep.Exists(email))
	    {
	        return false;
	    }
		
		
		
		 if (univ.getPack() == TypePackage.Standard)
	     {
	          stud.setNbLivreMensuel_Autorise(10);
	     }
	     else if (univ.getPack() == TypePackage.Premium)
	     {
	    	 stud.setNbLivreMensuel_Autorise(10*2);
	     }                           
	     
		 etudRep.add(stud);
		 journal.outPut_Msg("Log: Fin de l'op�ration d'ajout de l'�tudiant avec matricule "+matricule);
		 return true;
	    
		
	}
	
	
	

public ArrayList<Etudiant> GetEtudiantParUniversitye()
{
    //...
	return new ArrayList<>(4);
}

public ArrayList<Etudiant> GetEtudiatparLivreEmprunte()
{
    //...
	return new ArrayList<>(4);
	
}


	
}
