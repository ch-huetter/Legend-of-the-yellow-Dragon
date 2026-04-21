package de.game.service.filler.dto;

public interface DtoFiller<T> {

    public void fillDto (T dto);

    public Class<T> supports ();

}
