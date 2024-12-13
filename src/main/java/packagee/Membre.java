package packagee;
 class Membre {
     private static int idIncrement=0;
     private int id;
     private String nom, prenom, email, phone;
     private Incident[] incidents;
     public Membre(String nom,String prenom,String email,String phone){
         this.id=0;
         this.nom=nom;
         this.prenom=prenom;
         this.email=email;
         this.phone=phone;
     }
     public boolean equals(Membre B) {
         return B.id == this.id;
     }
     public String toString(){
         return "Membre: of id "+id+"Name: "+nom+" "+prenom+" , EMAIL: "+email+" ,PHONE: "+phone;
     }
     public int hashCode() {
         return (int) id * nom.hashCode() * email.hashCode();
     }

     public int getId() {
         return id;
     }

     public String getNom() {
         return nom;
     }

     public String getPrenom() {
         return prenom;
     }

     public String getEmail() {
         return email;
     }

     public String getPhone() {
         return phone;
     }

     public Incident[] getIncidents(){return incidents;}
     public void setId(int id){this.id=id;}
 }
