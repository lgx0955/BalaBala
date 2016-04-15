package cn.figo.mydemo.danmaku;

import java.util.List;

import master.flame.danmaku.danmaku.model.BaseDanmaku;


/**
 * Created by Lakeinchina(lakeinchina@hotmail.com) on 2015/11/26.
 * FloatingBili Project
 *
 * Copyright (C) 2015 Po Hu <lakeinchina@hotmail.com>
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
public interface IIncomingDanmakuCallback {
    public void incomingDanmaku(String danmaku);
    public void incomingDanmakus(List<BaseDanmaku> danmakus);
}
