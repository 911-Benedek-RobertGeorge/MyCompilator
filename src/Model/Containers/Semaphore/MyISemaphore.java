package Model.Containers.Semaphore;

import Model.Exceptions.ContainersException;

import java.util.Map;

public interface MyISemaphore<A, V> {


    void setContent(Map<A, V> refreshedHeap);

    Map<A, V> getContent();

    void add(A key, V value) throws ContainersException;

    int getFirstFreeLocation();

    V getValueOnAddress(A address);
}
