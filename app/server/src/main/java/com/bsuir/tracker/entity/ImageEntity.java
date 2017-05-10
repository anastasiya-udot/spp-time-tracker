package com.bsuir.tracker.entity;

import javax.persistence.*;

/**
 * Created by Pavel on 26.04.2017.
 */
@Entity
@Table(name = "image", schema = "spp-time-tracker", catalog = "")
public class ImageEntity {
    private int idimage;
    private String url;
    private String publicId;

    @Id
    @Column(name = "idimage", nullable = false)
    public int getIdimage() {
        return idimage;
    }

    public void setIdimage(int idimage) {
        if (idimage < 0){
            throw new IllegalArgumentException();
        }
        this.idimage = idimage;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "public_id", nullable = false, length = 255)
    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageEntity that = (ImageEntity) o;

        if (idimage != that.idimage) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (publicId != null ? !publicId.equals(that.publicId) : that.publicId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idimage;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (publicId != null ? publicId.hashCode() : 0);
        return result;
    }
}
