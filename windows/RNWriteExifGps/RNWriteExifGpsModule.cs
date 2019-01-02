using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Write.Exif.Gps.RNWriteExifGps
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNWriteExifGpsModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNWriteExifGpsModule"/>.
        /// </summary>
        internal RNWriteExifGpsModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNWriteExifGps";
            }
        }
    }
}
