package ghifari.pptb;

public class Blog1 {
    private String Foto;
    private String Namamakanan;
    private Long Jumlahkalori;

    public Blog1 ()
    {

    }

    public Blog1 (String Foto, String Namamakanan, Long Jumlahkalori){
        this.Foto = Foto;
        this.Namamakanan = Namamakanan;
        this.Jumlahkalori = Jumlahkalori;
    }




    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getNamamakanan() {
        return Namamakanan;
    }

    public void setNamamakanan(String namamakanan) {
        Namamakanan = namamakanan;
    }

    public Long getJumlahkalori() {
        return Jumlahkalori;
    }

    public void setJumlahkalori(Long jumlahkalori) {
        Jumlahkalori = jumlahkalori;
    }


}