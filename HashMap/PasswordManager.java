package a8;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "Forever21@";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        int hash = Math.abs(key.hashCode())%50;

        if (_passwords[hash]==null) {
            _passwords[hash] = new Account(key, value);
        }
        else {
            Account<K,V> entry = _passwords[hash];
            while (entry.getNext() !=null && !entry.getWebsite().equals(key)) {
                entry = entry.getNext();
            }
            if (entry.getWebsite().equals(key)) {
                entry.setPassword(value);
            }
            else {
                entry.setNext(new Account(key, value));
            }
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int hash = Math.abs(key.hashCode())%50;

        //potentially change this back to Account and cast?
        Account<K,V> head = _passwords[hash];
        while (head != null) {
            if (head.getWebsite().equals(key)) {
                return head.getPassword();
            }

            head = head.getNext();
        }

        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int count = 0;
        for (Account x: _passwords) {
            while (x != null) {
                count++;
                x = x.getNext();
            }
        }

        return count;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Account<K,V> x: _passwords) {
            Account<K,V> head = x;
            while (head != null) {
                keySet.add(head.getWebsite());
                head = head.getNext();
            }
        }
        return keySet;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int hash = Math.abs(key.hashCode())%50;

        if (_passwords[hash] != null) {
            Account<K,V> previous = null;
            Account<K,V> head = _passwords[hash];
            while (head.getNext() != null && !head.getWebsite().equals(key)) {
                previous = head;
                head = head.getNext();
            }
            if (head.getWebsite().equals(key)) {
                if (previous == null)
                    _passwords[hash] = head.getNext();
                else
                    previous.setNext(head.getNext());
                return (V) head.getPassword();
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }

    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> duplicates = new ArrayList<>();
        for (Account<K,V> x: _passwords) {
            Account<K,V> head = x;
            while (head != null) {
                if (head.getPassword().equals(value)) {
                    duplicates.add(head.getWebsite());
                }
                head = head.getNext();
            }
        }
        return duplicates;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return MASTER_PASSWORD.equals(enteredPassword);
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}