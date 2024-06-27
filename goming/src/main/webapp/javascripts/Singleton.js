class Singleton {
    constructor(value1, value2, value3) {
        if (Singleton.instance) {
            return Singleton.instance;
        }
        this._likeCounter = value1; // likeCounter 변수
        this._currentImageElement = value2; // currentImageElement 변수
        this._globalImageSrc = value3; // globalImageSrc 변수
        Singleton.instance = this;
        return this;
    }

    // getter 메서드
    get likeCounter() {
        return this._likeCounter;
    }

    get currentImageElement() {
        return this._currentImageElement;
    }

    get globalImageSrc() {
        return this._globalImageSrc;
    }

    // setter 메서드
    set likeCounter(value1) {
        this._likeCounter = value1;
    }

    set currentImageElement(value2) {
        this._currentImageElement = value2;
    }

    set globalImageSrc(value3) {
        this._globalImageSrc = value3;
    }
}


export default Singleton;