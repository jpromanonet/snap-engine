package org.esa.s3tbx.olci.harmonisation;

/**
 * Constants for OLCI O2A Harmonisation
 *
 * @author olafd
 */
class OlciHarmonisationConstants {

    static int OLCI_INVALID_BIT = 25;

    // for bands 13-15, taken from O2_tra2recti_model_coeff_*.json:
    static double[] cwvl = {761.726, 764.825, 767.917};

    //
    static double[][] DWL_CORR_OFFSET = {
            {
                    0.0,-0.1,-0.15,-0.15,-0.25
            },
            {
                    -0.05,-0.1,-0.15,-0.15,-0.25
            },
            {
                    0.0,-0.1,-0.15,-0.15,-0.25
            }
    };

    // for bands 13-15, taken from O2_tra2recti_model_coeff_*.json:
    static double[][] pCoeffsRectification = {
            {
                    1.9281088925186658,
                    0.8611327564752951,
                    0.2580503194681209,
                    -0.045993280418357915,
                    0.005358643551587891,
                    0.12659773028733481,
                    0.0,
                    -0.9116617176995471,
                    0.0,
                    0.0
            },
            {
                    2.512290463762116,
                    1.4511538250539846,
                    0.4940964173193233,
                    -0.022254534016777284,
                    0.002646811571508564,
                    0.15054629162744312,
                    0.0,
                    -1.502810421190935,
                    0.0,
                    0.0
            },
            {
                    27.74755282728319,
                    26.596747414918887,
                    11.689654182833268,
                    -0.001539147433752177,
                    0.0002400425859420771,
                    0.1852299228319541,
                    0.0,
                    -26.749581014760352,
                    0.0,
                    0.0
            }
    };

    // for bands 13-15, taken from O2_tra2press_model_coeff_*.json:
    static double[][] pCoeffsTra2Press = {
            {
                    1995.7143317076086,
                    -3769.722768661591,
                    1892.510020000023
            },
            {
                    2916.330126283431,
                    -4380.8993531697,
                    1455.8312812048223
            },
            {
                    -10289.226480975387,
                    33242.140025516775,
                    -23115.783448719812
            }
    };

    // for bands 13-15, taken from O2_press2tra_model_coeff_*.json:
    static double[][] pCoeffsPress2Tra = {
            {
                    0.010869397727187936,
                    0.0012156679747751865,
                    -7.893497010058784e-08
            },
            {
                    0.018204213100328395,
                    0.0005842669445858559,
                    2.8795827361591314e-08
            },
            {
                    0.01479036699920767,
                    7.047312427567494e-05,
                    3.2813610021198614e-08
            }
    };
}